# Experiment with postgres and clickhouse

The following exercice will try to demonstrate differences between row and
column databases. In this spirit, we will setup a clickhouse cluster. Importing
a data set from the french gouvernment.

After that, we will observe quickly where and how the
data is stored. 

## The dataset

Select a subject that you would like to study https://www.data.gouv.fr/fr/pages/thematiques-a-la-une/.

I'll choose to study buildings that are protected:
https://www.data.gouv.fr/fr/datasets/immeubles-proteges-au-titre-des-monuments-historiques-2/

Let's first create a clickhouse schema so that we can load our data.

A list of available datatypes in clickhouse: https://clickhouse.com/docs/en/sql-reference/data-types

```sql
CREATE TABLE buildings ( Domaine String, Lieudit String, Remploi String, `Région` String, Cadastre String, Copyright String, Reference String, Historique String, `Département` String, Observations String, Date_de_Label String, Liens_externes String, Cadre_de_l_etude String, Typologie_de_plan String, Type_de_couverture String, Adresse_forme_index String, Auteur_de_l_edifice String, Commune_forme_index String, Identifiant_Agregee String, Partie_constituante String, Typologie_du_dossier String, etat_de_conservation String, Datation_de_l_edifice String, Genre_du_destinataire String, Precision_affectataire String, Nature_de_la_protection String, Reference_a_un_ensemble String, Typologie_du_couvrement String, Adresse_forme_editoriale String, Commune_forme_editoriale String, Description_de_l_edifice String, Materiaux_du_gros_oeuvre String, Denomination_de_l_edifice String, Justification_attribution String, Lien_vers_la_base_Joconde String, Lien_vers_la_base_Palissy String, Materiaux_de_la_couverture String, Precision_de_la_protection String, Typologie_de_la_protection String, Lien_vers_la_base_Archiv_MH String, Personnes_liees_a_l_edifice String, coordonnees_au_format_WGS84 String, Departement_format_numerique String, Justification_de_la_datation String, Precision_de_la_localisation String, Titre_editorial_de_la_notice String, Date_de_creation_de_la_notice Date, Description_de_l_iconographie String, Partie_d_elevation_exterieure String, Precision_sur_la_denomination String, Statut_juridique_de_l_edifice String, Autre_appellation_de_l_edifice String, COG_Insee_lors_de_la_protection String, Date_de_la_derniere_mise_a_jour Date, Partie_constituante_non_etudiee String, Destination_actuelle_de_l_edifice String, Date_et_typologie_de_la_protection String, Typologie_de_la_zone_de_protection String, Elements_remarquables_dans_l_edifice String, Indexation_iconographique_normalisee String, Precision_sur_le_statut_de_l_edifice String, Vocable___pour_les_edifices_cultuels String, Description_de_l_elevation_interieure String, Technique_du_decor_porte_de_l_edifice String, Etablissement_affectataire_de_l_edifice String, Format_abrege_du_siecle_de_construction String, Source_de_l_energie_utilisee_par_l_edifice String, Couverts_ou_decouverts_du_jardin_de_l_edifice String, Emplacement__forme_et_structure_de_l_escalier String, References_des_parties_constituantes_etudiees String, Siecle_de_campagne_secondaire_de_construction String, Dimensions_normalisees_des_edicules_uniquement String, Siecle_de_la_campagne_principale_de_construction String, Nom_du_cours_d_eau_traversant_ou_bordant_l_edifice String, Renvoi_vers_une_notice_de_la_base_Merimee_ou_Palissy String, Lieu_de_conservation_d_un_element_architectural_deplace String
)
ENGINE = MergeTree
PARTITION BY toYear(Date_de_creation_de_la_notice)
ORDER BY (Reference, Date_de_creation_de_la_notice)
;
```
Let's insert some data now ! By change I found a parquet link in my data ! 
`INSERT INTO buildings SELECT * FROM s3('https://object.files.data.gouv.fr/hydra-parquet/hydra-parquet/a5366a7f3366110b0c1dde649ca465a6.parquet')`

## Observing storage in clickhouse

`docker ps` to find the id of our clickhouse and then `docker exec -it <id> bash` to obtain a bash in the container.

First let's navigate to `cd /var/lib/clickhouse/` folder. Inside you should find a `/data` folder.

```sh
> ls -lh
total 144K
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 1970_13_13_0
drwxr-x--- 2 clickhouse clickhouse  20K Nov 21 15:34 1993_1_1_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 1995_9_9_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 1996_10_10_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 1998_11_11_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 1999_12_12_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2001_14_14_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2002_15_15_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2003_16_16_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2004_17_17_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2005_18_18_0
drwxr-x--- 2 clickhouse clickhouse 4.0K Nov 21 15:34 2006_19_19_0
```
We can see that our table is stored in multiple files corresponding to our
partition key defined in our schema.

```sh
> ls -r 1970_13_13_0
serialization.json  partition.dat                             metadata_version.txt           data.cmrk3  count.txt    checksums.txt
primary.cidx        minmax_Date_de_creation_de_la_notice.idx  default_compression_codec.txt  data.bin    columns.txt
```

Looking closer in a folder, we can see the actual data `data.bin` and some
other files storing quick access for metadata like (counts, indices, columns,
etc). `.cmrk3` store indices.

In the folder `/metadata`, you will find schema of the databases. They are used
by the engine in order to make efficient queries.

`/store` is used by clickhouse to manage the repartition of the data between
replicas and data storage.

## Let's play with the data a bit
For example, let's display the number of entry by year.

```sql 
SELECT 
    toYear(Date_de_creation_de_la_notice) as year,
    length(groupArray(Reference)) as numberOfLocation
FROM buildings
GROUP BY year;


┌─year─┬─numberOfLocation─┐
│ 1970 │              135 │
│ 1993 │            37165 │
│ 1995 │              530 │
│ 1996 │             1080 │
│ 1998 │              496 │
│ 1999 │              512 │
│ 2001 │              581 │
│ 2002 │              707 │
│ 2003 │              354 │
│ 2004 │              280 │
│ 2005 │              242 │
│ 2006 │              248 │
│ 2007 │              335 │
│ 2008 │              321 │
│ 2009 │              212 │
│ 2010 │              279 │
│ 2011 │              239 │
│ 2012 │              241 │
│ 2013 │              260 │
│ 2014 │              181 │
│ 2015 │              242 │
│ 2016 │               10 │
│ 2017 │              525 │
│ 2018 │              113 │
│ 2019 │              321 │
│ 2020 │              129 │
│ 2021 │              160 │
│ 2022 │              189 │
│ 2023 │              224 │
│ 2024 │              161 │
└──────┴──────────────────┘
```

Now we will play with a nice feature of clickhouse the arrays. It enable us to
make really granular processing in queries. Let's display per region, the year
and number of locations.

```sql
SELECT
    `Région` as region,
    arrayDistinct(groupArray(toYear(Date_de_creation_de_la_notice))) as year,
    length(groupArray(Reference)) as numberOfLocation
FROM buildings
GROUP BY region ORDER BY region;

┌─region───────────────────────────────────────┬─year────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┬─numberOfLocation─┐
│ Auvergne-Rhône-Alpes                         │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,1993,2021,2006,1999,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             4959 │
│ Auvergne-Rhône-Alpes,Bourgogne-Franche-Comté │ [1993]                                                                                                                                                  │                1 │
│ Bourgogne-Franche-Comté                      │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,1993,2021,2006,1999,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             3738 │
│ Bretagne                                     │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             3218 │
│ Centre-Val de Loire                          │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             2860 │
│ Corse                                        │ [2017,2018,2020,2013,2015,2012,2008,2019,2021,1993,2024,1996,2022,1998,2009,2010,2011,1995]                                                             │              335 │
│ Grand Est                                    │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             4611 │
│ Guadeloupe                                   │ [2017,2018,2020,2013,2014,2015,2008,2019,2007,2006,1999,1993,1970,2016,1998,2009,2005,2003,2004]                                                        │              117 │
│ Guyane                                       │ [2017,2013,2014,2023,2021,1993,1996,2003,1995]                                                                                                          │               90 │
│ Hauts-de-France                              │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,1993,2021,2006,1999,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             3248 │
│ La Réunion                                   │ [2017,2020,2013,2015,2012,2008,2002,2023,2019,2007,1999,1993,1970,2024,1996,2022,1998,2009,2011,2003,1995]                                              │              201 │
│ Martinique                                   │ [2017,2020,2013,2014,2015,2012,2023,2019,2007,2021,1993,1996,1998,2010,2011,2005,1995]                                                                  │              124 │
│ Mayotte                                      │ [2017,2018,2020,2013,2019,2021]                                                                                                                         │               12 │
│ Normandie                                    │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             3091 │
│ Nouvelle-Aquitaine                           │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             6332 │
│ Occitanie                                    │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             5040 │
│ Pays de la Loire                             │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             2167 │
│ Provence-Alpes-Côte d'Azur                   │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,1998,2009,2010,2011,2005,2003,2004,1995]      │             2368 │
│ Saint-Pierre-et-Miquelon                     │ [2017,2020,2013,2012,2021]                                                                                                                              │               16 │
│ Île-de-France                                │ [2017,2018,2020,2013,2014,2015,2012,2008,2001,2002,2023,2019,2007,2021,2006,1999,1993,1970,2024,1996,2022,2016,1998,2009,2010,2011,2005,2003,2004,1995] │             3944 │
└──────────────────────────────────────────────┴─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┴──────────────────┘

```

Now let's compute the age of each locations. And then counting thoses than have
at least 100 years. Finally let's group them by region.

```sql 
WITH
  groupArray((Reference, toInt16(Datation_de_l_edifice))) as locationWithDate,
  arrayMap((ref, date) -> (ref, toYear(now()) - date), locationWithDate) as ages,
  arrayFilter((_, d) -> d > 100, ages) as moreThanACentenary
SELECT
  `Région` as region,
  -- ages,
  length(moreThanACentenary) nbMoreThanACentenary, length(ages) nbTotal
FROM buildings
WHERE
  length(Datation_de_l_edifice) == 4
GROUP BY region
;

┌─region─────────────────────┬─nbMoreThanACentenary─┬─nbTotal─┐
│ Corse                      │                   68 │      72 │
│ Guyane                     │                   15 │      15 │
│ Bretagne                   │                  512 │     544 │
│ Mayotte                    │                    1 │       2 │
│ Pays de la Loire           │                  220 │     240 │
│ Martinique                 │                   28 │      37 │
│ Guadeloupe                 │                   21 │      39 │
│ Normandie                  │                  349 │     415 │
│ La Réunion                 │                   41 │      56 │
│ Hauts-de-France            │                  443 │     588 │
│ Grand Est                  │                  977 │    1051 │
│ Île-de-France              │                  800 │     987 │
│ Occitanie                  │                  668 │     740 │
│ Bourgogne-Franche-Comté    │                  681 │     722 │
│ Auvergne-Rhône-Alpes       │                  669 │     743 │
│ Centre-Val de Loire        │                  416 │     454 │
│ Saint-Pierre-et-Miquelon   │                    8 │       9 │
│ Nouvelle-Aquitaine         │                  789 │     897 │
│ Provence-Alpes-Côte d'Azur │                  337 │     403 │
└────────────────────────────┴──────────────────────┴─────────┘
```


## Resources

arrays doc: https://clickhouse.com/docs/en/sql-reference/functions/array-functions

https://clickhouse.com/docs/knowledgebase/parquet-to-csv-json

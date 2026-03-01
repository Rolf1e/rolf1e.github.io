# Lombok

Le projet [Lombok](https://projectlombok.org/) est une librairie Java qui aide à rendre le code moins lourd et verbeux
de Java comme les constructeurs, getters, builders.

- `@Getter` crée les getters de tous les attributs de la classe. (Le même existe pour les `@Setter`).
- `@AllAgrsConstructor` un constructeur pour tous les attributs.
- `@NoAgrConstructor` un constructeur sans paramètre.
- `@RequiredArgsConstructor` un constructeur pour tous les attributs **requis** (aka `final` attributs).
- `@Builder` crée une inner class `Example.ExampleBuilder` permettant d'avoir un "pattern builder".
- `@Slf4j` permet d'instancier un logger pour la classe facilement. (Un logger fait des choses très complexes en Java).

## Que fait lombok ?

Lombok génère du code via les annotations de Java. Il va au moment de la compilation les remplacer par du code Java
répondant à ce qui a été décrit. Vous pouvez faire l'expérience vous-même en créant une classe Java avec une
annotation `@Getter` sur un attribut. Lancez la compilation et remarquez que dans le fichier `.class` (l'ouvrir à l'aide
de `javap` ou bien d'Intellij IDEA) le getter s'y trouve bien.

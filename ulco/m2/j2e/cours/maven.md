# Maven

[Apache Maven](https://maven.apache.org/) est un outil de gestion de projet, en particulier pour la gestion des
dépendances (via le classpath), du build, du lancement des tests et des versions (JVM, Classes, packages). Un exemple de
projet Maven se trouve dans la partie [spring](spring/spring.md). Il utilise XML pour décrire les actions à effectuer.

## How does it work ?

```shell
mvn clean         # détruit le dossier /target avec toutes les .class et autres fichiers utiles à la JVM.
mvn clean install # Install les dépendances listée dans le pom.xml, lance la compilation et les tests.
mvn test          # lance les tests
mvn package       # Construit le projet pour le release

java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App # lance le jar de notre application.
```

[Maven en 5 minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

Pour ajouter une dépendance à votre projet, dans votre `pom.xml` rajoutez ceci dans la balise `<dependencies>` pour
ajouter le driver JDBC pour postgresql.

```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.5.1</version>
</dependency>
```

### Pour aller plus loin

- [Intellij IDEA à la rescousse](https://www.jetbrains.com/help/idea/maven-support.html)
- [Maven et NodeJS](https://github.com/eirslett/frontend-maven-plugin)
- [Angular application avec Maven](https://medium.com/sparkles-blog/angular-in-the-enterprise-building-angular-apps-through-maven-3ca535152f85)


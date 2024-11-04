# Fast intro à Golang

## Les bases

```golang

    aInt := 12
    aString := "Hello World"
    anArrayOfString := []string { "" }
    aMap := make(map[string]int) // https://go.dev/blog/maps

    if aInt == 12 {
        // do something
    } else { 
        // do something
    }

    sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}

    import "fmt"
    for _, e := range anArrayOfString {
        fmt.Println(e)
    }

    import "time"

    today := time.Now().Weekday()

	switch time.Saturday {
	case today + 0:
		fmt.Println("Today.")
	case today + 1:
		fmt.Println("Tomorrow.")
	case today + 2:
		fmt.Println("In two days.")
	default:
		fmt.Println("Too far away.")
	}

```

La déclaration d'une fonction

```golang
func add(a, b int) int {
    return a + b
}
```
En Go, les objets ressemblent à des classes mais avec une syntaxe un peu
différente. Plus proche de la structure du C.

```golang
type Article struct {
	Title   string   `json:"title"`
	Date    string   `json:"date"`
	Content []string `json:"content"`
}

func (a Article) GetTitle() string { // Method example
    return a.Title
}

```

Les interfaces en Go, fonctione comme en Java, sauf qu'il n'y a pas de mot clé
`implements` à ajouter, mais se fait sur le nom des fonctions.

```golang
type Texte interface {
    GetMainTitle() string
}

func (a Article) GetMainTitle() string { // implementation example
    return a.GetTitle()
}
```

Golang possède un type spécifique pour la gestion d'erreur. Il s'utilise comme
n'importe quel autre type. Sa gestion a un pattern spécifique à golang comme
suit: 

```golang
import "fmt"

func aFuncWithError() (string, err) {
    return nil, fmt.Errorf("an error message")
}

func main() {
    _, err := aFuncWithError()
    if err != nil {
        // error handling here
    }
}

```

## HTTP rest

```golang
package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
)

func main() {

	http.HandleFunc("/articles", articleHandler)

	fmt.Println("Server is running at http://localhost:8080")
	log.Fatal(http.ListenAndServe(":8080", nil))

}

func articleHandler(w http.ResponseWriter, r *http.Request) {
	articles := []Article{
		{Title: "Article 1", Date: "2024-10-13", Content: []string{"1st page"}},
		{Title: "Article 2", Date: "2024-10-13", Content: []string{"1st page", "2nd page"}},
		{Title: "Article 3", Date: "2024-10-13", Content: []string{"1st page", "2nd page", "3rd Page"}},
		{},
	}

	json.NewEncoder(w).Encode(articles)
}
```

// TODO: concurrency https://go.dev/tour/concurrency/1

## Resources

https://go.dev/tour/welcome/1

package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
)

func main() {

	http.HandleFunc("/internal-server-error", internalServerErrorHandler)
	http.HandleFunc("/articles", articleHandler)

	http.Handle("/not-found", http.NotFoundHandler())

	fmt.Println("Server is running at http://localhost:8080")
	log.Fatal(http.ListenAndServe(":8080", nil))

}

type Article struct {
	Title   string   `json:"title"`
	Date    string   `json:"date"`
	Content []string `json:"content"`
}

func protobufHandler(w http.ResponseWriter, r *http.Request) {


}

func internalServerErrorHandler(w http.ResponseWriter, r *http.Request) {
	http.Error(w, "there has been a error when connecting to database", http.StatusInternalServerError)
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

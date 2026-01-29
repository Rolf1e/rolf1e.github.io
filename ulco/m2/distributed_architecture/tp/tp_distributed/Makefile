
proto: 
	go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
	protoc -I=. --go_out=. ./article.proto

local-run: proto
	go run main.go article.pb.go

docker-run: proto
	docker compuse up -d



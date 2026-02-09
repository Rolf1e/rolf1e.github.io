# Start from the official Go image
FROM golang:latest

# Set the Current Working Directory inside the container
WORKDIR /app

# Copy go.mod go.sum file
# Care, if you have no dependency in your app you'll have no go.sum
COPY go.mod go.sum ./

# Copy the source from the current directory to the Working Directory inside the container
COPY . .

# Build the Go app
RUN go build -o server main.go

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the executable
CMD ["/app/server"]

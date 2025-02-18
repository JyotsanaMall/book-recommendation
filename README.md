# Getting Started

### Overview
This is a book recommendation service which provides two primary endpoints, each serving a distinct purpose. The first endpoint utilizes a chat model to generate responses, while the second employs a Retrieval-Augmented Generation (RAG) approach to search for answers within a specified database. Both endpoints support system prompts and custom response formats to enhance the clarity and legibility of the responses.

### Endpoints

##### 1. Chat Model Endpoint
* **Endpoint URL:** /ask
* **Method:** POST
* **Description:** This endpoint leverages a chat model to generate responses based on user input. It is designed for conversational interactions where the model generates a response without relying on external data sources.
* **Request Format:** Uses the Question record.
* **Response Format:** Returns a BookList record.

##### 2. RAG (Retrieval-Augmented Generation) Endpoint
* **Endpoint URL:** /bookWithInfo
* **Method:** POST
* **Description:** This endpoint uses a Retrieval-Augmented Generation approach to search for answers within a provided database. It combines retrieval with generation to provide more accurate and contextually relevant responses.
* **Request Format:** Uses the Question record.
* **Response Format:** Returns a BookList record.

### System Prompts and Custom Response Formats
* **System Prompts:** Optional inputs that guide the model in generating responses. They can be used to set the tone, style, or focus of the response.
* **Custom Response Formats:** Specify the desired format of the response, such as plain text, bullet points, or JSON, to improve readability and meet specific requirements.

### Usage
curl --location 'localhost:9080/bookWithInfo' \
--header 'Content-Type: application/json' \
--data '{
"question": "Recommend top 5 fiction based on indian mythology"
}'

### Dataset
https://cseweb.ucsd.edu/~jmcauley/datasets/goodreads.html
Add the datasets to the resource path

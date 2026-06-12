const formData = new FormData();
formData.append("file", selectedFile);
await fetch("http://localhost:8080/arquivos", {
  method: "POST",
  body: formData
});
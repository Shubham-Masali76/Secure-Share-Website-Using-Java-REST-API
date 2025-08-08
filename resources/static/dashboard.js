const userId = sessionStorage.getItem("userId");
const username = sessionStorage.getItem("username");

if (!userId) {
  window.location.href = "login.html";
} else {
  document.getElementById("username").textContent = username;
  loadFiles();
}

function logout() {
  sessionStorage.clear();
  window.location.href = "login.html";
}

document.getElementById("uploadForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const file = document.getElementById("fileInput").files[0];
  if (!file) return;

  const formData = new FormData();
  formData.append("file", file);
  formData.append("userId", userId);

  fetch("http://localhost:8080/api/files/upload", {
    method: "POST",
    body: formData
  })
    .then(res => res.text())
    .then(msg => {
      alert(msg);
      loadFiles();
    })
    .catch(err => alert(err.message));
});

function loadFiles() {
  fetch(`http://localhost:8080/api/files/user/${userId}`)
    .then(res => res.json())
    .then(files => {
      const fileList = document.getElementById("fileList");
      fileList.innerHTML = "";

      files.forEach(file => {
        const li = document.createElement("li");
        li.textContent = file.originalFilename + " ";

        const downloadBtn = document.createElement("button");
        downloadBtn.textContent = "Download";
        downloadBtn.onclick = () => window.open(`http://localhost:8080/api/files/download/${file.id}`, "_blank");

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.onclick = () => deleteFile(file.id);

        li.appendChild(downloadBtn);
        li.appendChild(deleteBtn);
        fileList.appendChild(li);
      });
    });
}

function deleteFile(fileId) {
  fetch(`http://localhost:8080/api/files/delete/${fileId}`, {
    method: "DELETE"
  })
    .then(res => res.text())
    .then(msg => {
      alert(msg);
      loadFiles();
    })
    .catch(err => alert(err.message));
}

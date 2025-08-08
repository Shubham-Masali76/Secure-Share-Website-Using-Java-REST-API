document.getElementById("resetForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const data = {
    email: document.getElementById("email").value,
    newPassword: document.getElementById("newPassword").value
  };

  fetch("/api/users/reset-password", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
    .then(res => res.text())
    .then(msg => alert(msg))
    .catch(err => alert("Error: " + err));
});

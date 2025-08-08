document.getElementById("registerForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const user = {
    username: document.getElementById("username").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value
  };

  fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  })
    .then(res => {
      if (!res.ok) throw new Error("Registration failed");
      return res.text();
    })
    .then(msg => {
      alert(msg);
      window.location.href = "login.html";
    })
    .catch(err => alert(err.message));
});

document.getElementById("loginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const credentials = {
    email: document.getElementById("email").value,
    password: document.getElementById("password").value
  };

  fetch("http://localhost:8080/api/users/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(credentials)
  })
    .then(res => {
      if (!res.ok) throw new Error("Invalid credentials");
      return res.json();
    })
    .then(data => {
      sessionStorage.setItem("userId", data.id);
      sessionStorage.setItem("username", data.username);
      window.location.href = "dashboard.html";
    })
    .catch(err => alert(err.message));
});

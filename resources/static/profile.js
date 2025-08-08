document.addEventListener("DOMContentLoaded", () => {
    const loader = document.getElementById("loader");
    const section = document.getElementById("profileSection");

    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get("userId");

    if (!userId) {
        alert("User not logged in.");
        window.location.href = "login.html";
        return;
    }

    fetch(`/api/users/${userId}`)
        .then(res => res.json())
        .then(user => {
            loader.style.display = "none";
            section.style.display = "block";

            document.getElementById("userId").innerText = user.id;
            document.getElementById("userEmail").innerText = user.email;
            document.getElementById("username").value = user.username;
        })
        .catch(err => {
            loader.style.display = "none";
            alert("Error fetching profile: " + err);
        });

    document.getElementById("updateUsernameForm").addEventListener("submit", (e) => {
        e.preventDefault();
        const newUsername = document.getElementById("username").value;

        fetch(`/api/users/update-username/${userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username: newUsername })
        })
            .then(res => res.ok ? alert("Username updated!") : alert("Failed to update username"))
            .catch(err => alert("Error: " + err));
    });
});

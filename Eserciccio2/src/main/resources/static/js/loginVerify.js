document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Impedisce il comportamento predefinito del form

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch("/doLogin", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`,
    })
        .then((response) => {
            if (response.ok) {
                return response.json().then((data) => {
                    document.getElementById("message").innerHTML = `<span class="success">${data.message}</span>`;
                    // Reindirizza alla home page dopo 2 secondi
                    setTimeout(() => {
                        window.location.href = "/ristoranti";
                    }, 1000);
                });
            } else {
                return response.json().then((data) => {
                    document.getElementById("message").innerHTML = `<span class="error">${data.message}</span>`;
                });
            }
        })
        .catch((error) => {
            console.error("Errore:", error);
            document.getElementById("message").innerHTML = `<span class="error">Errore di connessione al server</span>`;
        });
});

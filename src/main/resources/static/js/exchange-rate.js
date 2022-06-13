function getExchangeRate() {
    let error = document.getElementById("countySelectError");
    if (error != null) {
        error.remove();
    }
    let selectCounty = document.getElementById("counties");
    let county = selectCounty.options[selectCounty.selectedIndex].value;
    console.log(county);
    axios({
        method: "get",
        url: "/exchange-rate/" + county
    })
        .then(response => {
            document.getElementById("exchangeRate").value = response.data.exchangeRate + " " + response.data.recipientCountry + "/USD";
        })
        .catch(error => {
            let div = document.getElementById("div-counties");
            let p = document.createElement("label");
            p.id = "countySelectError";
            p.style.color = "red";
            p.innerHTML = error.response.data;
            div.appendChild(p);
        })
}

function calculateExchangeRate() {
    let selectCounty = document.getElementById("counties");
    let country = selectCounty.options[selectCounty.selectedIndex].value;
    let remittance = document.getElementById("remittance").value;
    let result = document.getElementById("result");
    if (!/^(0|[-]?[1-9]\d*)$/.test(remittance)) {
        result.style.color = "red";
        result.innerHTML = "바르지 않은 송금액입니다.";
        return;
    }
    axios({
        method: "get",
        url: "/exchange-rate/" + country + "/" + remittance
    })
        .then(response => {
            result.style.color = "black";
            result.innerHTML =
                "수취금액은 " + response.data.amountReceived + " " + response.data.recipientCountry + " " + "입니다.";
        })
        .catch(error => {
            result.style.color = "red";
            result.innerHTML = error.response.data;
        });
}
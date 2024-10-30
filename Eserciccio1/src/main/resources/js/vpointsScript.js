const convertButton = document.getElementById("convertButton");
const moneyField = document.getElementById("moneyField");

const outputVPoint = document.getElementById("outputVPoints");

const skinsOutput=document.getElementById("skinsOutput");

const tableSkinsOutput=document.getElementById("tableSkins");

const totalSkinsOutput=document.getElementById("totalSkins");

const budgetSkinsOutput=document.getElementById("budgetSkins");

const skins = [
    {name: "Champions 2021 Karambit", price: 6263},
    {name: "Champions 2022 Knife", price: 5350},
    {name: "Champions 2022 Phantom", price: 2675},
    {name: "Chronovoid Vandal", price: 2175},
    {name: "Gaia Vengeance Vandal", price: 1775},
    {name: "Ion Operator", price: 1775},
    {name: "Origin Operator", price: 1775},
    {name: "Prelude to Chaos Vandal", price: 2175},
    {name: "Protocol Phantom", price: 2475},
    {name: "Radiant Phantom", price: 1775},
    {name: "Singularity Phantom", price: 2175},
    {name: "Spectrum Knife", price: 5350},
    {name: "Spectrum Phantom", price: 2675},
    {name: "Spline Operator", price: 1775},
    {name: "Reaver Karambit", price: 4350},
    {name: "Elderflame Collection", price: 9900},
    {name: "Glitchpop Collection", price: 8700}
];

let v=skins.find(o => o.name === 'Glitchpop Collection');

console.log(v.price);
let vpoints = 0;
let conv=false;
let budget= 0;
function converti() {
    let money = Number(moneyField.value);
    vpoints = 0;
    conv=true;
    while (money > 4) {
        if (money >= 100) {
            vpoints += 11000;
            money -= 100;
        } else if (money >= 50) {
            vpoints += 5350;
            money -= 50;
        } else if (money >= 35) {
            vpoints += 3650;
            money -= 35;
        } else if (money >= 20) {
            vpoints += 2050;
            money -= 20;
        } else if (money >= 10) {
            vpoints += 1000;
            money -= 10;
        } else if (money >= 5) {
            vpoints += 475;
            money -= 5;
        }
    }
    console.log(vpoints);
    return vpoints;
}

function printVPoints(e) {
    e.preventDefault();
    const points = converti()

    budget = vpoints;

    updateText(total, budget);
    outputVPoint.innerHTML = `
        <h3>Hai la bellezza di: <strong>${points}</strong> <br>VALORANT Points</h3>
    `;

}



for(let i=0; i<skins.length; i++){
    let HTMLString= `
    <div class="card " style="width: 15rem;" >
        <img class="card-img-top pt-2 clickable" id="${skins[i].name}" src="/image/Skins/${skins[i].name}.png" alt="Card image cap"/>
        <div class="card-body">
            <p class="card-text" style="text-align: center">${skins[i].name}<br/>Price: ${skins[i].price} VP</p>
        </div>
    </div>
    `;
    skinsOutput.insertAdjacentHTML("beforeend", HTMLString);
}

let total=0;

function delRow(t)
{
    let roww = t.parentNode.parentNode;
    console.log(roww);

    document.getElementById("tableSkins").deleteRow(roww.rowIndex);
    const row = t.closest('tr');
    const price = row.cells[1].textContent;
    let priceValue = Number(price);

    total-=priceValue;

    updateText(total, budget);
}

function updateText(total, budget){
    console.log("bud"+budget);
    console.log("tot"+total);

    totalSkinsOutput.innerHTML = `
        <h3>Totale spesa: <strong style="color:red">${total}</strong> VALORANT Points <br>
        `
    if (!conv) {
        budgetSkinsOutput.innerHTML = `<h5>Non hai inserito quanti soldi vorresti spendere su questo gioco.<h5>`
    } else if ((budget-total)<0 && conv){
        budgetSkinsOutput.innerHTML = `<h3>Hai finito i VPoints. Ti mancano: <strong style="color: green">${total-budget}</strong> VALORANT Points per prendere queste skin</h3>`;
    }else{
        budgetSkinsOutput.innerHTML = `<h3>Hai ancora a disposizione: <strong style="color: green">${budget-total}</strong> VALORANT Points</h3>`;

    }

}


window.onclick = e => {
    if (skins.find(o => o.name === e.target.id)) {
        let v = skins.find(o => o.name === e.target.id);
        console.log(v.price);
        const HTMLText = `
        <tr id="tab${e.target.id}">
            <td>
                ${e.target.id}
            </td>
            <td>
                ${v.price}
            </td>
            <td><button class="btn btn-danger btn-sm deleteButton" onclick="delRow(this)"><i class="fa-solid fa-trash"></i></button></td>
        <tr>
    `;
        tableSkinsOutput.insertAdjacentHTML("beforeend", HTMLText);

        total += Number(v.price);

        updateText(total, budget);

    }




}

convertButton.addEventListener("click", printVPoints);
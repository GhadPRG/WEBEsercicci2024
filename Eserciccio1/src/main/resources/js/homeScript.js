const operatori = document.getElementById("operatori");
const nameAndInfoForOperator = [
    {
        name: "Breach",
        description: `<p>E' un demolitore.<br>Il suo ruolo principale è quello distrarre gli avversari rallentandoli o accecandoli in modo da poter attaccare con più semplicità.</p>`
    },
    {
        name: "Brimstone",
        description: `<p>E' uno stratega.<br>Il suo ruolo principale è quello di fornire copertura ai propri compagni ostruendo la vista agli avversari, danneggiarli oppure velocizzando i movimenti degli alleati.</p>`
    },
    {
        name: "Deadlock",
        description: `<p>E' una guardiana.<br>Il suo ruolo principale è quello di ostacolare o catturare i nemici in modo da rallentarli.</p>`
    },
    {
        name: "Fade",
        description: `<p>E' una demolitrice.<br>Il suo ruolo principale è quello ottenere informazioni sulle posizioni dei nemici e di distrarli.</p>`
    },
    {
        name: "Harbor",
        description: `<p>E' uno stratega.<br>Il suo ruolo principale è quello di rallentare o ostruire la visione dei nemici.</p>`
    },
    {
        name: "Iso",
        description: `<p>E' un assassino.<br>Il suo ruolo principale è quello di uccidere gli avversari, di danneggiarli molto gravemente o di aiutare gli alleati a prendere spazio in attacco.</p>`
    },
    {
        name: "Jett",
        description: `<p>E' un'assassina.<br>Il suo ruolo principale è quello uccidere gli avversari.</p>`
    },
    {
        name: "Phoenix",
        description: `<p>E' un Assassino.<br>Il suo ruolo principale è quello di uccidere gli avversari utilizzando le sue abilità che gli permettono di accecare i nemici o bruciarli per danneggiarli.</p>`
    },
    {
        name: "Sage",
        description: `<p>E' una guardiana.<br>Il suo ruolo principale è quello di curare i propri compagni oppure rallentare i nemici.</p>`
    },
    {
        name: "Skye",
        description: `<p>E' una demolitrice.<br>Il suo ruolo principale è quello di ottenere informazioni sui nemici accecandoli o rallentandoli e ha la possibilità di curare gli alleati.</p>`
    },
    {
        name: "Sova",
        description: `<p>E' un demolitore.<br>Il suo ruolo principale è quello ottenere informazioni sulle posizioni degli avversari in modo da poter creare un attacco efficace e senza sorprese.</p>`
    },
    {
        name: "viper",
        description: `<p>E' una stratega.<br>Il suo ruolo principale è quello di controllare il campo di battaglia bloccando la visione nemica oppure danneggiandoli tramite veleno.</p>`
    },
    {
        name: "Vyse",
        description: `<p>E' una guardiana.<br>Il suo ruolo principale è quello rallentare o danneggiare i nemici, in modo da fargli perdere più tempo possibile prima di attaccare.</p>`
    }
];



for(let i=0; i<nameAndInfoForOperator.length; i++){
    HTMLString= `
        <div class="row border rounded my-1" style="background-color: azure">
            <div class="col-sm-5 py-2 my-auto">
                <img src="/image/Operatori/${nameAndInfoForOperator[i].name}.webp" class="rounded-4 py-auto mx-auto d-block" width="35%" height="90%">
            </div>
            <div class="col my-auto">
                <h1 style="text-align: center">${nameAndInfoForOperator[i].name}</h1>
                ${nameAndInfoForOperator[i].description}
            </div>
        </div>
    `;
    operatori.insertAdjacentHTML("beforeend", HTMLString);
}

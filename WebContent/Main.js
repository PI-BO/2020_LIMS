import Partner from './partner/Partner.js'

let mainContent = document.getElementById("main-content");



console.log(mainContent)

const partner = new Partner();


partner.render(mainContent);


import Partner from './partner/Partner.js';
import NavigationMenu from './navigationMenu/NavigationMenu.js';
import Events from './Events.js';

const mainContent = document.getElementById("main-content");
const mainMenu = document.getElementById("main-menu");
mainMenu.addEventListener(Events.PARTNER.ERSTELLEN , () => partner.render(mainContent));

const partner = new Partner();



console.log(mainContent)

// partner.addEventListener(Test, (event) => console.log(event));



NavigationMenu.render(mainMenu);
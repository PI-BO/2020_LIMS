import Listenable from "./Listenable.js";

export default class ViewModel extends Listenable{

    constructor(address){
        super();
        this.showDelay = 500;
        if(address !== undefined) this.html = this.fetchAddress(address);
    }

    async render(htmlElementId) {

        const htmlElement = document.getElementById(htmlElementId);
        await this.html;
        htmlElement.innerHTML = this.html;
        this.init();
    }

    fetchAddress(address){

        return new Promise((resolve) => {
            fetch(address, {
                method: "post",
            })
                .then(response => response.text())
                .then(response => {
                    this.html = response;
                    resolve();
                });
        })
    }
}
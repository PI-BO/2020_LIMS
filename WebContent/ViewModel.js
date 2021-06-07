import Listenable from "./Listenable.js";

export default class ViewModel extends Listenable{

    constructor(){
        super();
        this.showDelay = 500;
    }
}
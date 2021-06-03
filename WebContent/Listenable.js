export default class Listenable{

    constructor() {
        this.eventListener = [];
    }

    addEventListener(event, callback) {
        if (this.eventListener[event] === undefined) this.eventListener[event] = [];
        this.eventListener[event].push(callback);
    }

    dispatchEvent(event) {
        const eventType = event.type;
        if (this.eventListener[eventType] === undefined) return;
        this.eventListener[eventType].forEach(listener => {
            let data = {};
            if (event.data !== undefined) data = event.data;
            listener(data);
            console.log("dispatchEvent:", {event});
        });
    }
    
}
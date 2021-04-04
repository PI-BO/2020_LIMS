const Tooltip = ( function (){

    public.setTooltip = function setTooltip(elementId, tooltipText){

        const element = document.getElementById(elementId);

        element.classList.add("tooltip")

        const tooltipLink = document.createElement("a");
        tooltipLink.href = "javascript:void(0);";
        tooltipLink.innerText = "?";
        element.appendChild(tooltipLink);

        const tooltipDiv = document.createElement("div");
        tooltipDiv.classList.add("tooltiptext");
        tooltipDiv.innerText = tooltipText;
        element.appendChild(tooltipDiv);

    }

    return public;

})();
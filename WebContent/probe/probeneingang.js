// load small preview pictures when files have been selected
function loadFile(input) {
    $("#preview-container").empty();

    for (let i = 0; i < input.files.length; i++) {
        const objectURL = URL.createObjectURL(input.files[i]);
        $("#preview-container").append(
            "<div class='image-container'>" +
            "<img class='preview-image' style='width:100%; height=100%' src=" + objectURL + ">" +
            "</div>"
        );
        $(".preview-image").attr({onload: "freeMemory(this);"});
    }
}

function freeMemory(element) {
    URL.revokeObjectURL(element.src);
}

function replaceContent(id, text, color) {
    const element = document.getElementById(id);
    element.style.color = color;
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    element.append(text);
}
function deleteEingangsanalytikMethode(methode) {
	
	let parent = methode.parentElement;
	while(parent.className != "eingangsanalyse_methode"){
		parent = parent.parentElement;
	}
	parent.remove();
}

function addEingangsanalytikMethode() {
  let list = document.getElementById("eingangsanalyse_methoden_list");

  let node = document.createElement("DIV");
  node.setAttribute("class", "eingangsanalyse_methode");
  
  let template = document.getElementById("template_methode");
  
  template = template.firstElementChild;
  template = template.cloneNode(true);
  
  node.appendChild(template);
  list.appendChild(node);
}
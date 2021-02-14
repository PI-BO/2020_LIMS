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
  
  let bla = `
  
        <div class="eingangsanalyse_methode_row eingangsanalyse_methode_main_row">

          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_main_header">
              <select required>
                <option value="" selected disabled>bitte auswaehlen</option>
                <option value="">PXRD D2</option>
                <option value="">PXRD D8</option>
                <option value="">DSC</option>
                <option value="">TG</option>
                <option value="">IT</option>
                <option value="">1H-NMR</option>
              </select>
            </div>
          </div>
          <div class="eingangsanalyse_methode_delete_button">
            <input type="button" value=" entfernen " onclick="deleteEingangsanalytikMethode(this)">
          </div>
        </div>

        <div class="eingangsanalyse_methode_row">
          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_header">Auswahl</div>
            <div class="eingangsanalyse_entry">
              <select required>
                <option value="" selected>nein</option>
                <option value="">ja</option>
              </select>
            </div>
          </div>

          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_header">Operator</div>
            <div class="eingangsanalyse_entry"><input type="text"></div>
          </div>

          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_header">Parameter</div>
            <div class="eingangsanalyse_entry"><input type="text" value="Standard"></div>
          </div>

          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_header">Messfile</div>
            <div class="eingangsanalyse_entry"><input type="text"></div>
          </div>

          <div class="eingangsanalyse_methode_column">
            <div class="eingangsanalyse_header">Status</div>
            <div class="eingangsanalyse_entry">
              <select required>
                <option value="" selected>offen</option>
                <option value="">abgeschlossen</option>
              </select>
            </div>
          </div>

        </div>  
  `;
  
  node.innerHTML = bla;
  list.appendChild(node);
}
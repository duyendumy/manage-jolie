var name = document.getElementById("kw");
var search = document.getElementById("search");


search.onclick = function() {
	var name = document.getElementById("kw");
	var parser = new DOMParser();
	var htmlContent = name.value;
	var parsedDocument = parser.parseFromString(htmlContent, 'text/html');
	var scriptElements = parsedDocument.getElementsByTagName('script');

	for (var i = 0; i < scriptElements.length; i++) {
		var script = document.createElement("script");
		script.textContent = scriptElements[i].textContent;
		document.body.appendChild(script);
	}
};




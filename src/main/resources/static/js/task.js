var submit = document.getElementById("submittt");


submit.onclick = function() {
	//var name = document.getElementById("name");
	const test = "<script>alert('hello')</script>";
	var display = document.getElementById("display");
	var table = document.getElementById("myTable");
	if (test != "") {
		var row = table.insertRow(-1); // Thêm hàng mới vào cuối bảng
		var cell = row.insertCell(0);
		var cell_2 = row.insertCell(1);
		var cell_3 = row.insertCell(1);
		var cell_4 = row.insertCell(1);
		var checkbox = document.createElement("input");
		checkbox.type = "checkbox";
		var checkbox_2 = document.createElement("input");
		checkbox_2.type = "checkbox";
		var checkbox_3 = document.createElement("input");
		checkbox_3.type = "checkbox";
		var checkbox_4 = document.createElement("input");
		checkbox_4.type = "checkbox";
		cell_2.appendChild(checkbox_2);
		cell_3.appendChild(checkbox_3);
		cell_4.appendChild(checkbox_4);
		cell.textContent = test;
	}

	var parser = new DOMParser();
	var htmlContent =test;
	var parsedDocument = parser.parseFromString(htmlContent,
		'text/html');
	var scriptElements = parsedDocument.getElementsByTagName('script');

	for (var i = 0; i < scriptElements.length; i++) {
		var script = document.createElement("script");
		script.textContent = scriptElements[i].textContent;
		document.body.appendChild(script);
	}
} 
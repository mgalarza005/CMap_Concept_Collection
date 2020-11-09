document.getElementById('file').onchange = function(){

  var file = this.files[0];

  var reader = new FileReader();
  reader.onload = function(progressEvent){
    // Entire file
    console.log(this.result);
	document.getElementById("demo").innerHTML = 5 + 6;
    // By lines
    var lines = this.result.split('\n');
    for(var line = 0; line < lines.length; line++){
    	
    	
    	var res = lines[line].split(":");
   		document.getElementById("concepts").innerHTML =res[0] + "\n";
    	
    	console.log(res[0]);
    	
    	//console.log(lines[line]);  lerroz lerro
    }
  };
  reader.readAsText(file);
};
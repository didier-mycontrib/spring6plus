//************** SPECIF CRUD ********

window.onload=function(){
	//(document.getElementById("inputId")).disabled=true; //if auto_incr
	initListeners(); 
}

function objectFromInput(){
	let code = (document.getElementById("inputId")).value;
	let name = (document.getElementById("inputName")).value;
	let rate = (document.getElementById("inputRate")).value;
	
	document.getElementById("spanMsg").innerHTML="";
	
	let obj = { code : code,
				   name : name,
	               rate : Number(rate) }
	return obj;
}

function displayObject(obj){
	(document.getElementById("inputId")).value=obj.code;
	(document.getElementById("inputName")).value=obj.name;
	(document.getElementById("inputRate")).value=obj.rate;
}

function insertRowCells(row,obj){
	(row.insertCell(0)).innerHTML = obj.code;
	(row.insertCell(1)).innerHTML = obj.name;
	(row.insertCell(2)).innerHTML = obj.rate;
}


function blankObject(){
	return {code:"" , name: "" , rate : ""};	
}

function getWsBaseUrl(){
	return "./rest/bank-api/devise";	
}

//obj = object with values to check
//action = "add" or "update" or ...
function canDoAction(action,obj){
	ok=true; //by default
	if(obj.code==null || obj.code == "")
	  ok=false;
	if(obj.rate==null || obj.rate == "") 
	   ok=false;
	if(obj.name==null || obj.name == "") 
	   ok=false;
	return ok;
}



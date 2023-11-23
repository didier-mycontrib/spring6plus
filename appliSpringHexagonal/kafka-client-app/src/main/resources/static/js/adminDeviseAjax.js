//************** SPECIF CRUD ********

window.onload=function(){
	//(document.getElementById("inputId")).disabled=true; //if auto_incr
	initListeners(); 
}


var selectedRow = null;
var selectedObject = null;

function initListeners(){
	(document.getElementById("btnAdd"))
	   .addEventListener("click",addNew); 
	   
	(document.getElementById("btnReset"))
	   .addEventListener("click",resetObject); 
}

function resetObject(){
	document.getElementById("spanMsg").innerHTML="";
	document.getElementById("spanMsg").style.color="black";
	if(selectedRow!=null){
		selectedRow.style.color="black";
		selectedRow=null;
		selectedObject = null;
	}
	displayObject(blankObject());	
}

function addNew(){	
	let objectJs = objectFromInput();
	if(!canDoAction("add" , objectJs))return;
	let objectJson = JSON.stringify(objectJs) ;  
	let wsUrl = getWsBaseUrl();   
	makeAjaxPostRequest(wsUrl,objectJson,function (responseJson){
		console.log("responseJson="+responseJson);
		document.getElementById("spanMsg").innerHTML=responseJson;
	},basicErrorCallback);         
}

function basicErrorCallback(err){
	console.log("err="+err);
	if(err && err.length>256){
		err = err.substring(0,256)
	}
	document.getElementById("spanMsg").innerHTML=err;
	document.getElementById("spanMsg").style.color="red";
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

/*
function insertRowCells(row,obj){
	(row.insertCell(0)).innerHTML = obj.code;
	(row.insertCell(1)).innerHTML = obj.name;
	(row.insertCell(2)).innerHTML = obj.rate;
}
*/

function blankObject(){
	return {code:"USD" , name: "dollar" , rate : "1.1"};	
}

function getWsBaseUrl(){
	return "./rest/api-devise/admin-devise";	
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



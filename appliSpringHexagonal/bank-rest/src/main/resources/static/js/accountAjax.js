//************** SPECIF CRUD ********

window.onload=function(){
	(document.getElementById("inputId")).disabled=true; //if auto_incr
	initListeners(); 
}

function objectFromInput(){
	let id = (document.getElementById("inputId")).value;
	if(id=="")id=null;
	
	let label = (document.getElementById("inputLabel")).value;
	let initialBalance = (document.getElementById("inputInitialBalance")).value;
	
	document.getElementById("spanMsg").innerHTML="";
	let obj = { id : id,
				label : label,
	            balance : parseFloat(initialBalance)
	            };
	return obj;
}

function displayObject(obj){
	(document.getElementById("inputId")).value=obj.id;
	(document.getElementById("inputLabel")).value=obj.label;
	(document.getElementById("inputInitialBalance")).value=obj.balance;
}

function insertRowCells(row,obj){
	(row.insertCell(0)).innerHTML = obj.id;
	(row.insertCell(1)).innerHTML = obj.label;
	(row.insertCell(2)).innerHTML = obj.balance;
}


function blankObject(){
	return {id:"" , label: "" , balance : 0 };	
}

function getWsBaseUrl(){
	return "./rest/bank-api/account";	
}

//obj = object with values to check
//action = "add" or "update" or ...
function canDoAction(action,obj){
	ok=true; //by default
	if(obj.label==null || obj.label == "")
	  ok=false;
	if(obj.balance==null)
	  ok=false;
	return ok;
}

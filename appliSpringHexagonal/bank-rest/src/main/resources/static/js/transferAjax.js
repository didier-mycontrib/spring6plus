
window.onload=function(){
	(document.getElementById("btnRefreshAll"))
	   .addEventListener("click",refreshAll);
	   
	(document.getElementById("btnVirement"))
	   .addEventListener("click",doVirement); 
}

function basicErrorCallback(err){
	console.log("err="+err);
	if(err && err.length>256){
		err = err.substring(0,256)
	}
	document.getElementById("spanMsg").innerHTML=err;
	document.getElementById("spanMsg").style.color="red";
}

function initSelectOptions(selectId,comptes){
	let selectCpt = document.getElementById(selectId);
	selectCpt.innerHTML="";
	for(let cpt of comptes){
			let option=document.createElement("option");
			option.innerHTML=JSON.stringify(cpt)
			option.value=cpt.id;
			selectCpt.appendChild(option);
		}
}

function refreshAll(){	
	let customerId = document.getElementById("inputCustomerId").value;
	let wsUrl =  "./rest/bank-api/account?customerId=" + customerId;
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let objectListJs = JSON.parse(responseJson);
		console.log("objectListJs="+objectListJs);
		initSelectOptions("selectCptDeb",objectListJs);
		initSelectOptions("selectCptCred",objectListJs);
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let obj of objectListJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = obj.id;
			(row.insertCell(1)).innerHTML = obj.label;
			(row.insertCell(2)).innerHTML = obj.balance;
			(row.insertCell(3)).innerHTML = customerId;
		}
	},basicErrorCallback);
	
}

function virementFromInput(){
	let montant = (document.getElementById("inputMontant")).value;
	
	let numCptDeb = (document.getElementById("selectCptDeb")).value;
	let numCptCred = (document.getElementById("selectCptCred")).value;
	if(numCptDeb == numCptCred){
	    basicErrorCallback("numCompteDebit doit etre different de numCompteCredit");
	    return null;
	    }
	if(montant <= 0){
	    basicErrorCallback("le montant doit etre strictement positif");
	    return null;
	    }
	
	document.getElementById("spanMsg").innerHTML="";
	let virement = { amount : montant,
				sourceAccountId : numCptDeb,
	            targetAccountId : numCptCred
	            };
	return virement;
}

function doVirement(){	
	let virementJs = virementFromInput();
	if(virementJs==null) return;
	let objectJson = JSON.stringify(virementJs) ;  
	let wsUrl = "./rest/bank-api/movement/transfer";   
	makeAjaxPostRequest(wsUrl,objectJson,function (responseJson){
		console.log("responseJson="+responseJson);
		let virementResponseJs = JSON.parse(responseJson);
		let contextMsg = ` [montant=${virementResponseJs.amount} numCompteDebit=${virementResponseJs.sourceAccountId} numCompteCredit=${virementResponseJs.targetAccountId}]`
		document.getElementById("spanMsg").innerHTML=virementResponseJs.message + contextMsg;
	    document.getElementById("spanMsg").style.color="blue";
	    (document.getElementById("inputMontant")).value=0;
		refreshAll(); //pour rafraîchir le tableau avec objet ajouté
	},basicErrorCallback);         
}




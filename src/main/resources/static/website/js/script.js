  function openNav() {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}


function closeNav() {
    document.getElementById("mySidebar").style.width = "1px";
    document.getElementById("main").style.marginLeft = "0";
}
  
  
function clone_eintragen(){
          InputText = document.getElementById("clone_name").value;
      document.getElementById('clone').innerHTML=InputText;
    
}

  
function darkmode() {
    var element = document.body;
    element.classList.toggle("dark-mode");
}
  

function myFunction() {
      var input, filter, ul, li, a, i, txtValue;
      input = document.getElementById("myInput");

      
  
      filter =  input.value.toUpperCase();
  
      ul = document.getElementById("myUL");
  
      li = ul.getElementsByTagName("li");
  
      for (i = 0; i < li.length; i++) {

          a = li[i].getElementsByTagName("a")[0];

          txtValue = a.textContent || a.innerText;

          if (txtValue.toUpperCase().indexOf(filter) > -1) {
              li[i].style.display = "";
          } else {
              li[i].style.display = "none";
          }

          if(input === "hallo"){
            darkmode()
          }
      }

      document.getElementById("mySidebar").style.width = "500px";


}


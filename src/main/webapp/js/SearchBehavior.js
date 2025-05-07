//SearchBehavior.js

function showContent() {
  var selectedValue = document.getElementById("selectBox").value;
  
//  // 全てのコンテンツを非表示にする
//  var contents = document.getElementsByClassName("content");
//  for (var i = 0; i < contents.length; i++) {
//    contents[i].style.display = "none";
//  }

  // 選択されたコンテンツを表示
  if (selectedValue) {
    document.getElementById(selectedValue).style.display = "block";
  }
}
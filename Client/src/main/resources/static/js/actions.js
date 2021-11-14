function showInbox() {
    var inbox = document.getElementById('receivedMails');
    inbox.style.display = "block";
    var sent = document.getElementById('sentMails');
    sent.style.display = "none";
    $('#inbox-button').addClass("sidebarOption__active");
    $('#sent-button').removeClass("sidebarOption__active");
}

function showSent(){
    var inbox = document.getElementById('receivedMails');
    inbox.style.display = "none";
    var sent = document.getElementById('sentMails');
    sent.style.display = "block";

    $('#sent-button').addClass("sidebarOption__active");
    $('#inbox-button').removeClass("sidebarOption__active");
}


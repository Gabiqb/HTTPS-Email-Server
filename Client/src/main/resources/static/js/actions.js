function showInbox() {

    var inbox = document.getElementById('receivedMails');
    inbox.style.display = "block";
    var sent = document.getElementById('sentMails');
    sent.style.display = "none";
    const fromField = document.querySelector('#fromField');
    fromField.textContent="From";
    $('#inbox-button').addClass("sidebarOption__active");
    $('#sent-button').removeClass("sidebarOption__active");

}

function showSent(){

    var inbox = document.getElementById('receivedMails');
    inbox.style.display = "none";
    var sent = document.getElementById('sentMails');
    sent.style.display = "block";
    const fromField = document.querySelector('#fromField');
    fromField.textContent="To";
    $('#sent-button').addClass("sidebarOption__active");
    $('#inbox-button').removeClass("sidebarOption__active");

}

$(document).ready(function() {
    $('#viewMailModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var sender = button.data('sender') // Extract info from data-* attributes
        var reveiver = button.data('receiver')
        var subject = button.data('subject')
        var text = button.data('text')
        var time = button.data('time')
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Mail')
        modal.find('#senderLabel').text('Sender: ' + sender)
        modal.find('#receiverLabel').text('Receiver: ' + reveiver)
        modal.find('#subjectLabel').text('Subject: ' + subject)
        modal.find('#timeLabel').text(time)
        modal.find('#message-text').text(text)
    })


})

$(document).ready(function() {
    $("#search-text").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".emailRow").filter(function(){
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
})

$(document).ready(function() {

    $('#deleteButton').click(function() {

        /*$.ajax({
            url: "https://localhost:8082/deleteMails/",
            type: "post",
            data: $('.mail-checkbox:checked').serialize(),
            success: function(data) {
                console.log(data);
            }
        });*/

        var checkedMailsArray = []
        $("input:checkbox:checked").each(function(){
            checkedMailsArray.push($(this).val());
        });

        console.log(checkedMailsArray)

        $.ajax({
            url: "https://localhost:8082/deleteMails",
            type: "POST",
            dataType: 'json',
            data: JSON.stringify(checkedMailsArray),
            succes: function(response){
                location.reload();
            }
        })


    });
})
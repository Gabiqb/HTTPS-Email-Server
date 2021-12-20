
function showInbox() {
        var inbox = document.getElementById('receivedMails');
        inbox.style.display = "block";
        var sent = document.getElementById('sentMails');
        sent.style.display = "none";
        var drafts= document.getElementById('draftMails');
        drafts.style.display = "none";
        const fromField = document.querySelector('#fromField');
        fromField.textContent = "From";
        $('#inbox-button').addClass("sidebarOption__active");
        $('#sent-button').removeClass("sidebarOption__active");
        $('#draft-button').removeClass("sidebarOption__active");
        localStorage.setItem("tab","Inbox");
}

function showSent() {

        var inbox = document.getElementById('receivedMails');
        inbox.style.display = "none";
        var drafts= document.getElementById('draftMails');
        drafts.style.display = "none";
        var sent = document.getElementById('sentMails');
        sent.style.display = "block";
        const fromField = document.querySelector('#fromField');
        fromField.textContent = "To";
        $('#sent-button').addClass("sidebarOption__active");
        $('#inbox-button').removeClass("sidebarOption__active");
        $('#draft-button').removeClass("sidebarOption__active");

        localStorage.setItem("tab","Sent");
}
function showDrafts() {
    var inbox = document.getElementById('receivedMails');
    inbox.style.display = "none";
    var sent = document.getElementById('sentMails');
    sent.style.display = "none";
    var drafts= document.getElementById('draftMails');
    drafts.style.display = "block";
    const fromField = document.querySelector('#fromField');
    fromField.textContent = "To";
    $('#draft-button').addClass("sidebarOption__active");
    $('#sent-button').removeClass("sidebarOption__active");
    $('#inbox-button').removeClass("sidebarOption__active");
    localStorage.setItem("tab","Drafts");
}
$(document).click(function (e) {
    if ($(e.target).is('#myModal')) {
        $('#myModal').fadeOut(500);
    }

});
var myModal=document.getElementById('myModal')
window.onclick=function(e){

    if(e.target==modal){
        myModal.style.display="none"
    }
}

$(document).ready(function() {
    $('#viewMailModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var sender = button.data('sender') // Extract info from data-* attributes
        var receiver = button.data('receiver')
        var subject = button.data('subject')
        var text = button.data('text')
        var time = button.data('time')

        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Mail')
        modal.find('#senderLabel').text('Sender: ' + sender)
        modal.find('#receiverLabel').text('Receiver: ' + receiver)
        modal.find('#subjectLabel').text('Subject: ' + subject)
        modal.find('#timeLabel').text(time)
        modal.find('#message-text').text(text)

    })
    $('#viewAccountInfoModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var email = button.data('email')
        var address = button.data('address')
        var telephone = button.data('telephone')
        var age = button.data('age')
        var gender = button.data('gender')
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Account Information')
        modal.find('#emailLabel').text('Email: ' + email)
        modal.find('#nameLabel').text('Name: ' + name)
        modal.find('#telephoneLabel').text('Telephone: ' + telephone)
        modal.find('#addressLabel').text('Address: ' + address)
        modal.find('#ageLabel').text('Age: ' + age)
        modal.find('#genderLabel').text('Gender: ' + gender)

    })
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var id = button.data('id') // Extract info from data-* attributes
        var to = button.data('receiver')
        var subject = button.data('subject')
        var text = button.data('text')
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.

        $('.modal-compose-message-internal #to-input-field').val(to)
        $('.modal-compose-message-internal #subject-input-field').val(subject)
        $('.modal-compose-message-internal #text-input-field').val(text)
        $('.modal-compose-message-internal #draftID').val(id)
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

        var checkedMailsArray = []
        $("input:checkbox:checked").each(function(){
            checkedMailsArray.push($(this).val());

        });

        console.log(checkedMailsArray)

        $.ajax({
            url: "https://localhost:8082/deleteMails",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(checkedMailsArray),
            success:[function ()
            {
                $("input:checkbox:checked").each(function(){
                    $(this).closest(".emailRow").remove();
                });
            }]
        })



    });
})
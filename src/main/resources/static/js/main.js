$(document).ready(function () {
    $('.table .editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        console.log(href);
        $.get(href, function (news, status) {
            $('#idEdit').val(news.id);
            $('#titleEdit').val(news.title);
            $('#imageUrlEdit').val(news.imageUrl);
            $('#descriptionEdit').val(news.description);
        });

        $('#titleEditMessage').hide();
        $('#imageUrlEditMessage').hide();
        $('#descriptionEditMessage').hide();

        $('#editModal').modal();
    });

});

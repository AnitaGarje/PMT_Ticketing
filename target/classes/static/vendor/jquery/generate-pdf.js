var doc = new jsPDF('p', 'mm', [74, 90]);
var specialElementHandlers = {
    '#content': function (element, renderer) {
        return true;
    }
};


$('#cmd').click(function () {
    doc.fromHTML($('#content').html(), 15, 15, {
        'width': 170,
            'elementHandlers': specialElementHandlers
    });
    doc.save('ticket.pdf');
});
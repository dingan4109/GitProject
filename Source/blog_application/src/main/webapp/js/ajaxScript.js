function search() {
    debugger
    let searchByTitle = $('#searchByTitle').val();
    $.ajax({
        type: 'GET',
        data: {
            title: searchByTitle
        },
        dataType: 'json',
        url: 'http://localhost:8080/api/blogs/search',
        success:
        //     function () {
        //     alert('All good');
        // }

            function (blogList) {
                debugger
                let content = '';
                for (let i = 0; i < blogList.length; i++) {
                    content += `<div class="col-md-3">
                    <div class="container">
                        <img src="${blogList[i].image}" class="img-fluid">
                    </div>
                    <div class="container pt-4">
                        <h3>${blogList[i].title}</h3>
                        <p><a href="">See more</a></p>
                        <br>
                        <p>${blogList[i].description}</p>
                    </div>
                </div>`;
                }
                document.getElementById('blogs').innerHTML = content;
            }
    });

}
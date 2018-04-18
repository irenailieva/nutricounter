$(document).ready(function () {
    loadFirstJSONFoods();
    $('#foodSearchBox').on('input', searchFoods)
});

function loadFirstJSONFoods() {

    let username = $('#navbarUser').text();
    $.ajax({
        type: 'GET',
        url: '/foods/first10',
        data: {username: username},
        success: function (foundFoods) {
            loadFoundFoodsInDOM(foundFoods);
        }
    });
}

function loadFoundFoodsInDOM(foods) {

    $('#foodResultsUnorderedList').empty();
    $.each(foods, function(i, food) {
        let foodId = food.id;
        let foodName = food.name;
        $('#foodResultsUnorderedList')
            .append($('<a data-toggle="list" class="list-group-item list-group-item-action flex-column align-items-start py-2"></a>')
                .attr("foodId", foodId)
                .append($('<div class="d-flex w-100 justify-content-between"></div>')
                    .append($('<p id="foodDetails" class="my-auto"></p>')
                        .text(foodName)
                    )
                    .append($('<div class="form-group col-sm-4 mb-0"></div>')
                        .append($('<div class="input-group input-group-sm"></div>')
                            .append($('<input id="amount" type="number" step="0.01" class="form-control"/>'))
                            .append($('<div class="input-group-append"></div>')
                                .append('<div class="input-group-text">g</div>')
                                .append($('<button type="button" class="btn btn-outline-dark">+</button>')
                                    .attr('onclick', 'addFoodFromSearchToRecipe(\'' + foodId + '\')')
                                )
                            )
                        )
                    )
                )
            );
    });
}

function removeFoodFromRecipe(foodId) {
    let foodElementToRemove = $('#addedFoodsUnorderedList').find($('a[foodId=' + foodId + ']')).first();
    foodElementToRemove.remove();
}

function addFoodFromSearchToRecipe(foodId) {
    let foodElementToAdd = $('#foodResultsUnorderedList').find($('a[foodId=' + foodId + ']')).clone();
    foodElementToAdd.find('div.form-group.col-sm-4.mb-0').addClass('col-sm-5').removeClass('col-sm-4');
    foodElementToAdd.find('button').text('-').removeAttr('onclick').attr('onclick', 'removeFoodFromRecipe(\'' + foodId + '\')');
    foodElementToAdd.removeClass('active').removeClass('show');
    let amount = foodElementToAdd.find('#amount').val();
    console.log(amount);
    foodElementToAdd.append($('<input type="hidden"/>').attr('name', foodId).attr('value', amount));

    $('#addedFoodsUnorderedList').prepend(foodElementToAdd);

}

function searchFoods() {
    let searchWord = $('#foodSearchBox').val();
    let username = $('#navbarUser').text();
    $.ajax({
        type: 'GET',
        url: '/foods/search',
        data: {searchWord: searchWord, username: username},
        contentType: 'application/json',
        success: foundFoods => loadFoundFoodsInDOM(foundFoods)
    })
}

function appendCreateRecipeDetails() {

    let recipeCreateForm = $('#recipeCreateForm');
    let recipeData = JSON.stringify(recipeCreateForm.serializeArray());
    recipeCreateForm.append($('<input type="hidden" id="recipeData" name="recipeDataJSON"/>').attr('value', recipeData));
    //console.log(recipeData);

    //console.log('executed');
}

function validateRecipeCreateForm() {

    let recipeNameInput = $('#recipeName');

    if (recipeNameInput.val() === '') {

        if (!recipeNameInput.hasClass('is-invalid')) {
            recipeNameInput.addClass('is-invalid');
        }

        $('#recipeNameHelp').text('Recipe name cannot be empty!');
        console.log($('#recipeNameHelp').text());

        return false;
    }
}
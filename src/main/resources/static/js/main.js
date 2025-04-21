function toggleQuestion(element) {
    var toggleForm = element.parentElement;
    var children = toggleForm.parentElement.children;

    for (let i = 0; i < children.length; i++) {
        if(children[i].tagName=="FORM"){
            if(children[i].hidden) {
                children[i].hidden = false;
                toggleForm.remove();
            }
        }
    }
}
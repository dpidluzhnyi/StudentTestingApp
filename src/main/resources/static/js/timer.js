    function addMinutes(date, minutes) {
        return new Date(date.getTime() + minutes*60000);
    }

    function parseDate(date){
        let millis = Date.parse(date);
        return new Date(millis);
    }

    function updateTimer(date) {
        var now = Date.now();
        var countDownTime = addMinutes(parseDate(date), timeToComplete);

        console.log(countDownTime);

        var distance = countDownTime - now;

        console.log(distance);

        if (distance < 0) {
            document.getElementById("test-form").submit();
        }

        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("timer").innerHTML = hours + "h "
            + minutes + "m " + seconds + "s ";
    }

    function startTimer(date) {
        setInterval(updateTimer, 1000, date);
    }

    var timeToComplete = document.getElementById('timeToComplete').value;

function CreateScienceplan()
{
    const Name = document.getElementById('name');
    const Funding = document.getElementById('funding');
    const Objective = document.getElementById('objective');
    const Starsystem = document.getElementById('starsystem');
    const Datewrite = document.getElementById('datewrite');
    const Time = document.getElementById('timecreate');
    const Location = document.getElementById('location');
    const Filetype = document.getElementById('file');
    const Filequality = document.getElementById('quality');
    const Color = document.getElementById('color')


    var slider = document.getElementById("slidequality");
    var output = document.getElementById("quality");
    output.innerHTML = slider.value;

    slider.oninput = function() {
          output.innerHTML = this.value;
     }
}


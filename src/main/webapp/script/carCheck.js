$(document).ready(function(){

    if($('body').tooltip){
        $('body').tooltip({ selector: '[data-toggle="tooltip"]' });
    }

    $('#searchBtn').click(function(){
        console.log('on searchBtn click');
        var yearOpt     = $('#yearOption'),
            makeOpt     = $('#makeOption'),
            modelOpt    = $('#modelOption'),
            displayList = $('#display_list'),
            html        = '',
            isValid     = validateForm();
        if(!isValid){
            return;
        }
        displayList.html(html);
        $.getJSON(
            'car-check',
            {
                year    : yearOpt.val(),
                make    : makeOpt.val(),
                model   : modelOpt.val()
            },
            function(json){
                console.log('on callback: %o', json);
                if(json.success){
                    var data        = json.data,
                        carCheck    = null;
                    html += '<table style="width: 100%; height: 100%; padding: 20px; border-collapse: collapse;">';
                    html += '<tr><th class="year-section">Year</th><th>Trim</th><th>Name</th></tr>';

                    for(var i=0; i<data.length; i++){
                        carCheck = data[i];
                        html += '<tr class="border-top-solid"><td class="year-section">'+ carCheck.year + '</td>';
                        for(var j=0; j<carCheck.styles.length; j++){
                            if(j>0){
                                html += '</tr><tr><td></td>';
                            }
                            html += '<td>' + carCheck.styles[j].trim + '</td>';
                            html += '<td>' + carCheck.styles[j].name + '</td>';
                        }
                        html += '</tr>';
                    }
                    html += '</table>';
                    displayList.html(html);
                }else{
                    alert(json.message);
                }
            }
        );
    });

    validateForm = function(){
        var form            = $($('.search-form')[0]),
            requiredFields  = form.find('.required'),
            requiredField   = null,
            errorMsg        = '',
            isValid         = true;
        for(var i=0; i<requiredFields.length; i++){
            requiredField = $(requiredFields[i]);
            if(requiredField.val()){
                errorMsg = ''
            }else{
                errorMsg = 'is required';
            }
            isValid = isValid && !errorMsg
            updateValidationComponent(requiredField, '.form-group', '.validation-cmp', errorMsg);
        }
        return isValid;
    };

    updateValidationComponent = function(cmp, parentCls, validationCls, errorMsg){
        var parent          = cmp.parents(parentCls),
            validationCmp   = parent.find(validationCls);
        if(validationCmp.length > 0){
            if(errorMsg){
                validationCmp.css('display', 'inline-block');
                validationCmp[0].setAttribute('data-original-title', validationCmp[0].getAttribute('field-name') + ' ' + errorMsg.trim() + '.');
            }else{
                validationCmp.css('display', 'none');
            }
            parent[errorMsg ? 'addClass' : 'removeClass']('has-error');
        }
    };
})
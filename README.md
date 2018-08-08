# Project Guidelines

## 1.1 File naming

### 1.1.1 Image naming

| Asset Type                      | Prefix           | Example                                      |                
|---------------------------------|------------------|----------------------------------------------|
| Icons                           | `ic_`            | `ic_like.png, ic_like_active.png, ic_like_circle, ic_like_red` |   
| Background                      | `bg_`            | `bg_worldcup_campaign.png`                   |                
| Button                          | `btn_`           | `btn_search_red.png, btn_search_active.png`  |
| PlaceHolder                     | `ph_x_w_h`       | `ph_banner_16_9.png`                         |                                               



### 1.1.2 UI naming

| Element     | Example                           |
|-------------|-----------------------------------|
| `Button`    | `searchButton`                    |
| `ImageView` | `profileImageView`                |
| `TextView`  | `titleTextView`                   |
| `UILabel`   | `userNameLabel`                   |
| `UIButton`  | `searchButton`                    |

### 1.1.3 Class naming

### View (Activity, Fragment, Dialog, Adapter, UIViewController, UITableView)

#### Preferred
```
    - WorldCupActivity
    - WorldCupViewController
```  

#### Not Preferred:
```
    - WorldCupAct
    - WCActivity
    - WCAct
    - WCViewController
    - WorldCupController
    - WCVC
```  

### Presenter
```
    - WorldCupPresenter
```  

### ViewModel
```
    - WorldCupViewModel
```

### UseCase
``` 
    - StreamerUseCase 
    - FingerprintUseCase
```

### Repository
``` 
    - UserProfileRepository 
    - SurveyRepository
```

### Interface, protocol
``` 
    - StreamerUseCase 
    - StreamerUseCaseProtocol
```
### Interface, protocol (Listener, Callback)
```
    iOS
    - ViewDelegate (onSuccess, onFail)
    - DataDelegate (onSuccess, onFail)
    
    Android
    - AdapterListener (event from view)
    - DataCallback (callback data) 
```

### Order function
```
    - static 
    - lifecycle 
    - override 
    - public 
    - internal 
    - private
    - fileprivate 
    - (bus, notification center)
```

### if else condition
 ```
    if (true) {
        doSomething()
    }
    
    if (true) {
        doSomething()
    } else {
        doSomething()
    }
```

### Short if
```
    iOS
    - if (condition) ? 0 : 1
    
    Android
    - if (condition) 0 else 1
```

### if multiple condition
```
    if (condition1 && condition2 && condition3 && condition4 && condition5 
        && condition6 && condition7 && condition8 && condition9 && condition10 {
    
    }
```

### Function multiple parameter
```
    fun someFunction(parameter1: String, parameter2: String, parameter3: String,
                    parameter4: String, parameter5: String, callback: Callback) {
            
        }
```

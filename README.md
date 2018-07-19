# Project Guidelines

## 1.1 File naming

### 1.1.1 Image naming

| Asset Type                      | Prefix           | Example                                      |                
|---------------------------------|------------------|----------------------------------------------|
| Icons                           | `ic_`            | `ic_like.png, ic_like_active.png, ic_like_circle, ic_like_red` |   
| Background                      | `bg_`            | `bg_worldcup_campaign.png`                   |                
| Button                          | `btn_`           | `btn_search_red.png, btn_search_active.png`  |
| PlaceHolder                      | `ph_x_w_h`       | `ph_banner_16_9.png`                         |                                               



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

### Interface, protocal
``` 
    - StreamerUseCase 
    - StreamerUseCaseProtocol
```

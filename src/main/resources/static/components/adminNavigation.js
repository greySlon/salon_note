class AdminNavigation extends React.Component{
    constructor(props){
        super(props);
        this.state={credentials:this.props.credentials};
    }
    className="hide";
    render(){
         //this.className=(this.state.credentials.role==="ROLE_ADMIN")?"admin-navigation":"hide";
         if(this.state.credentials.role==="ROLE_ADMIN")
            return   <div className={this.className}>
                      <button>Добавить</button>
                      <button>Изменить</button>
                      <button>Расписание</button>
                     </div>;
         else
            return "";
        }
}
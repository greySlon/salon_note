class Container extends React.Component{
    constructor(props){
        super(props);
        this.renderLoginForm=this.renderLoginForm.bind(this);
        this.renderManagerWidjet=this.renderManagerWidjet.bind(this);
        this.renderEmployeeWidjet=this.renderEmployeeWidjet.bind(this);
        this.renderAdminWidjet=this.renderAdminWidjet.bind(this);
        this.auth=this.auth.bind(this);
        this.state={render:this.renderLoginForm(), credentials:this.credentials, masters:[{"id":1},{"id":2},{"id":3}]};
    }
    credentials={login:"", password:"", role:""};
    auth(isOk){
        console.log("Container.auth="+isOk);
        console.log(this.state);
        console.log("Container.auth="+"login:"+this.state.credentials.login);
        console.log("Container.auth="+"; pass:"+this.state.credentials.password);
        console.log("Container.auth="+"role:"+this.state.credentials.role);
        this.setState({render:this.renderManagerWidjet()});
    }
    renderAdminWidjet(){
        return <div>
                 <div className="admin-navigation">
                  <button>Добавить</button>
                  <button>Изменить</button>
                  <button>Расписание</button>
                 </div>
                 <Master credentials={this.credentials} masters={this.state.masters} />
               </div>;
    }
    renderEmployeeWidjet(){
        return <Master credentials={this.credentials} masters={this.state.masters} />;
    }
    renderManagerWidjet(){
        return this.state.credentials.role=="ROLE_ADMIN*"
        ?this.renderAdminWidjet()
        :this.renderEmployeeWidjet();
    }
    renderLoginForm(){
    console.log(this.state);
        return <LogForm auth={this.auth} credentials={this.credentials} />;
    }

    render(){
        return this.state.render;
    }
}
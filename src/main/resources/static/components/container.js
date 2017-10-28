class Container extends React.Component{
    constructor(props){
        super(props);
        this.auth=this.auth.bind(this);
        this.state={credentials:{}, masters:[]};
    }
    auth(credentials){
        console.log("Container#auth:"+credentials.role);
        [{"id":1},{"id":2},{"id":3}].forEach((item)=>this.state.masters.push(item));

        this.setState({credentials:credentials, masters:this.state.masters});
        console.log("Container#auth:"+this.state.masters.length);
    }

    render(){
        return <div>
                  <LogForm credentials={this.state.credentials} auth={this.auth} />
                  <AdminNavigation credentials={this.state.credentials} />
                  <Master credentials={this.state.credentials} masters={this.state.masters} />
               </div>
    }
}
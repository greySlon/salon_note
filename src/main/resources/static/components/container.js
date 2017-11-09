class Container extends React.Component{
    constructor(props){
        super(props);
        this.authorizeCallback=this.authorizeCallback.bind(this);
        this.masterCallback=this.masterCallback.bind(this);
        this.renderLogForm=this.renderLogForm.bind(this);
        this.state={credentials:{}, masters:[], week:{}};
    }

    authorizeCallback(){
        console.log("Container#authorizeCallback:"+this.state.credentials.role);

        fetch("/salon/master/all")
        .then(function(response){
          return response.json();
        })
        .then(masterResponse=>{
            masterResponse.MASTER_LIST.forEach((item, i)=>{
                this.state.masters.push(item)
            });
            this.setState({credentials:this.state.credentials, masters:this.state.masters});
        });
    }

    masterCallback(masterId){
        fetch("/salon/workitem/get_week?master_id="+masterId+"&week=0&date=12-02-2017")
                    .then(function(response){ return response.json();})
                    .then(scheduleResponse => {
                         this.state.week.schedule=scheduleResponse.WEEK_SCHEDULE;
                         this.setState({week:this.state.week});
                         console.log("Container#masterCallback.then");
                    });
    }

    renderLogForm(){
        if(!this.state.credentials.role)
            return <LogForm credentials={this.state.credentials} authorizeCallback={this.authorizeCallback} />;
    }

    render(){
        return <div>
                  {this.renderLogForm()}
                  <AdminNavigation credentials={this.state.credentials} />
                  <Master credentials={this.state.credentials} masters={this.state.masters} callback={this.masterCallback} />
                  <Week week={this.state.week} />
               </div>
    }
}
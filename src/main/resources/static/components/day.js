class Day extends React.Component{
    constructor(props){
        super(props);
        this.state={day:this.props.day, schedule:this.props.schedule};
        this.renderWorkItem=this.renderWorkItem.bind(this);
        this.renderComment=this.renderComment.bind(this);
        this.renderProcedures=this.renderProcedures.bind(this);
        this.renderSchedule=this.renderSchedule.bind(this);
    }

    renderComment(comment){
        if(comment)
            return <div class="comment">{workItem.comment}</div>;
    }

    renderProcedures(procedures){
        return procedures.map(item => <span className="time">{item.name}.</span>);
    }

    renderWorkItem(workItem){
            return <div>
                      <div>
                           <span className="time">{workItem.service_time}-</span>
                           {this.renderProcedures(workItem.procedures)}
                       </div>
                       {this.renderComment(workItem.comment)}
                       <hr />
                   </div>;
    }

    renderSchedule(){
        return this.state.schedule.workItems.map((item,i)=> this.renderWorkItem(item));
    }

    render(){
        console.log("Day#render:"+this.state.day);
        return <div class="schedule">
                    <h2>{this.state.day} ({this.state.schedule.date})</h2>
                    {this.renderSchedule()}
               </div>;
    }
}
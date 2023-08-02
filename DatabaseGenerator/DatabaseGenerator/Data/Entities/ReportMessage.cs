namespace DatabaseGenerator.Data.Entities
{
	public class ReportMessage
	{
		public int Id { get; set; }
		public string MessageText { get; set; }
		public int AccountId { get; set; }
		public virtual Account {get; set;}

	}
}

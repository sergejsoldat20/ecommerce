﻿namespace DatabaseGenerator.Data.Entities
{
	public class Comment
	{ 
		public int Id { get; set; }
		public string Text { get; set; }
		public DateTime CreationTime { get; set; }
		public Account Account { get; set; }	
		public int AccountId { get; set; }
		public Product Product { get; set; }
		public int ProductId { get; set; }
	}
}

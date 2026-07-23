/*
 * package gregtechfoodoption.tools;
 * 
 * 
 * public class ToolButcheryKnifeHVBehavior implements IToolBehavior {
 * 
 * private static final long use = GTValues.V[GTValues.HV * 4];
 * 
 * @Override
 * public float getBaseDamage(ItemStack stack) {
 * return super.getBaseDamage(stack) + 5;
 * }
 * 
 * @Override
 * public float getAttackSpeed(ItemStack stack) {
 * if (stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(use))
 * return super.getAttackSpeed(stack) + 3;
 * else
 * return 0;
 * }
 * 
 * @Override
 * public List<EnchantmentData> getEnchantments(ItemStack stack) {
 * return Lists.newArrayList(new EnchantmentData(Enchantments.LOOTING, 5));
 * }
 * 
 * @Override
 * public boolean canPerformSweepAttack(ItemStack stack) {
 * return stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).canUse(use);
 * }
 * 
 * public ItemStack getBrokenStack(ItemStack stack) {
 * IElectricItem electricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
 * return MetaItems.POWER_UNIT_HV.getChargedStackWithOverride(electricItem);
 * }
 * 
 * @Override
 * public float getMaxDurabilityMultiplier(ItemStack stack) {
 * return 4;
 * }
 * 
 * @Override
 * public boolean canMineBlock(IBlockState block, ItemStack stack) {
 * return (block.getMaterial() == Material.LEAVES) || (block.getMaterial() == Material.WEB);
 * }
 * }
 */
